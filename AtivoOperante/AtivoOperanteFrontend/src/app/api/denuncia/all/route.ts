import { NextResponse } from 'next/server';

export async function GET(request: Request) {
    try {
        const token = request.headers.get('cookie')?.split('; ')
            .find(row => row.startsWith('token='))?.split('=')[1];

        const response = await fetch('http://localhost:8080/api/denuncia/all', {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            }
        });

        if (!response.ok) {
            const errorData = await response.text();
            console.error('Backend error response:', errorData);
            return NextResponse.json(
                { message: 'Failed to fetch denuncias', details: errorData },
                { status: response.status }
            );
        }

        const data = await response.json();
        
        // Clean up the response to prevent circular references
        const cleanedData = data.map((denuncia: any) => ({
            id: denuncia.id,
            titulo: denuncia.titulo,
            texto: denuncia.texto,
            urgencia: denuncia.urgencia,
            data: denuncia.data,
            tipo: denuncia.tipo,
            feedBack: denuncia.feedBack ? {
                texto: denuncia.feedBack.texto
            } : null,
            usuario: denuncia.usuario
        }));

        return NextResponse.json(cleanedData);
    } catch (error) {
        console.error('Error fetching denuncias:', error);
        return NextResponse.json(
            { message: 'Failed to fetch denuncias', details: error instanceof Error ? error.message : 'Unknown error' },
            { status: 500 }
        );
    }
} 